using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EntityAttributes : MonoBehaviour
{
    public float maxHealth = 100;
    public float currentHealth;
    bool dead;

    // Ragdoll
    public GameObject defaultModel;
    public GameObject ragdoll;
    public RagdollLimbActivation[] limbs;
    public float ragdollForceMult = 100;
    Rigidbody lastLimbHit;
    Vector3 lastDamage;
    bool forceApplied;
    public float dismemberForce = 100;

    // UI
    public GameObject healthUI;
    public float maxLength = 1;

    // Start is called before the first frame update
    void Start()
    {
        currentHealth = maxHealth;
        limbs = GetComponentsInChildren<RagdollLimbActivation>();
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        if (lastLimbHit != null && dead && !forceApplied)
        {
            lastLimbHit.AddForce(lastDamage * ragdollForceMult);
            forceApplied = true;
        }
    }

    void UpdateHealthUI()
    {
        healthUI.transform.localScale 
            = new Vector3(currentHealth / maxHealth, 1, 1);
    }

    public float TakeDamage(Vector3 damageDirection, Rigidbody lastLimb = null, DamagableLimb dLimb = null)
    {
        currentHealth -= damageDirection.magnitude;
        if(currentHealth <= 0)
        {
            defaultModel.SetActive(false);
            foreach(RagdollLimbActivation limb in limbs)
            {
                limb.SetRagdollTransform();
            }
            ragdoll.SetActive(true);
            dead = true;
            lastLimbHit = lastLimb;
            lastDamage = damageDirection;
            healthUI.SetActive(false);
            //Dismember
            if(dLimb.dismemberedLimb !=null)
            {
                lastLimbHit.transform.localScale = Vector3.zero;
                dLimb.dismemberedLimb.transform.parent = null;
                if(dLimb.dismemberedLimb.GetComponent<HingeJoint>())
                {
                    Destroy(dLimb.dismemberedLimb.GetComponent<HingeJoint>());
                }
                dLimb.dismemberedLimb.SetActive(true);
                dLimb.dismemberedLimb.GetComponent<Rigidbody>().AddForce(
                    damageDirection.normalized * dismemberForce);
            }          
        }
        UpdateHealthUI();
        return currentHealth;
    }
}
