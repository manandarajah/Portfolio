using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RaycastWeapon : MonoBehaviour, IWeapon
{
    public WeaponData data;
    public Transform muzzle;
    public LayerMask damageLayer;
    public GameObject bulletTrace;

    public void SetData(WeaponData _data)
    {
        data = _data;
    }
    public void FireWeapon()
    {
        RaycastHit hit;
        GameObject traceInstance = Instantiate(bulletTrace, muzzle.position, muzzle.rotation);
        if (Physics.Raycast(muzzle.position, muzzle.forward, out hit, data.range, damageLayer))
        {
            if(hit.collider.gameObject.GetComponent<IDamagable>() != null)
            {
                hit.collider.gameObject.GetComponent<IDamagable>().TakeDamage(data.damage * muzzle.forward, hit.point);
            }
            //traceInstance.GetComponent<LineRenderer>().SetPosition(1, hit.point - muzzle.position);
            traceInstance.transform.localScale = new Vector3(1, 1, (hit.point - muzzle.position).magnitude);
        }
        else
        {
            //traceInstance.GetComponent<LineRenderer>().SetPosition(1, muzzle.forward * data.range);
            traceInstance.transform.localScale = new Vector3(1, 1, data.range);
        }
    }
}
