using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WeaponController : MonoBehaviour
{
    // Data
    public WeaponData data;
    public IWeapon weapon;
    public CharacterAnimatorHandler anim;
    public Transform leftHandObj;
    public Transform rightHandObj;

    int bulletsRemaining;
    // Start is called before the first frame update
    void Start()
    {
        weapon = GetComponent<IWeapon>();
        weapon.SetData(data);
        bulletsRemaining = data.magazineSize;
    }

    // Update is called once per frame
    void Update()
    {
        //if(firing && bulletsRemaining > 0)
        //{
        //    weapon.FireWeapon();
        //    bulletsRemaining--;
        //}
    }

    IEnumerator ShootCoroutine()
    {
        while (bulletsRemaining > 0)
        {
            weapon.FireWeapon();
            bulletsRemaining--;
            yield return new WaitForSeconds(1 / data.fireRate);
        }
        yield return null;
    }

    public void Fire()
    {
        StartCoroutine("ShootCoroutine");
    }

    public void StopFiring()
    {
        StopCoroutine("ShootCoroutine");
    }

    public void FinishReload()
    {
        bulletsRemaining = data.magazineSize;
    }

    public void Reload()
    {
        StopFiring();
        anim.PlayReload(true);
    }
}
