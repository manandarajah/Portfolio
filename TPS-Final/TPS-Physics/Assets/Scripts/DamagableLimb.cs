using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DamagableLimb : MonoBehaviour, IDamagable
{
    public float damageMultiplier = 1;
    public EntityAttributes entity;


    // Limb Dismemberment
    public GameObject dismemberedLimb;

    public  void TakeDamage(Vector3 damageDirection, Vector3 pointOfImpact)
    {
        entity.TakeDamage(damageMultiplier * damageDirection, GetComponent<RagdollLimbActivation>().ragdollLimb.GetComponent<Rigidbody>(), this);
    }
}
