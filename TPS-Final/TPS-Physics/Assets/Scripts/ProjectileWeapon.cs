using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ProjectileWeapon : MonoBehaviour, IWeapon
{
    public WeaponData data;
    public Transform muzzle;
    public LayerMask damageLayer;
    public GameObject projectilePrefab;

    public void SetData(WeaponData _data)
    {
        data = _data;
    }
    public void FireWeapon()
    {
        GameObject instProjectile = Instantiate(projectilePrefab,
                                                muzzle.position,
                                                muzzle.rotation);
        instProjectile.GetComponent<Projectile>().damage = data.damage;
        instProjectile.GetComponent<Projectile>().layersToHit = damageLayer;
        instProjectile.GetComponent<Rigidbody>().velocity =  data.range * muzzle.forward;
    }
}
