using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;

public class Projectile : MonoBehaviour
{
    public float damage;
    public float projectileRange;
    public LayerMask layersToHit;

    private void OnCollisionEnter(Collision collision)
    {
        List<Collider> collidersHit = Physics.OverlapSphere(transform.position, projectileRange).ToList();
        List<Collider> collidersVisible = new List<Collider>();

        foreach (Collider col in collidersHit)
        {
            RaycastHit hit;
            if(Physics.Raycast(transform.position, 
                col.transform.position - transform.position,
                out hit,
                (col.transform.position - transform.position).magnitude,
                layersToHit))
            {
                if (hit.collider == col)
                {
                    collidersVisible.Add(col);
                }
            }
        }

        foreach (Collider col in collidersVisible)
        {
            if(col.GetComponent<IDamagable>() != null)
            {
                Vector3 toVec = col.transform.position - transform.position;
                col.GetComponent<IDamagable>().TakeDamage(
                    toVec.normalized * damage / Mathf.Pow(toVec.magnitude, 1),
                    col.transform.position);
            }
        }

        Destroy(gameObject);
    }
}
