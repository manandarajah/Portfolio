using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerWeaponController : MonoBehaviour
{
    public List<WeaponController> weapons = new List<WeaponController>();
    int currentWeaponIndex = 0;
    public WeaponController weaponCtrl;
    public CharacterIKHandler ikHandler;
    public CharacterAnimatorHandler animHandler;
    public CursorLockMode wantedMode;

    // Start is called before the first frame update
    void Start()
    {
        SwitchWeapon();
        Cursor.lockState = wantedMode;
        // Hide cursor when locking
        Cursor.visible = (CursorLockMode.Locked != wantedMode);
    }

    // Update is called once per frame
    void Update()
    {
        Shoot();
        Reload();
        SwitchWeapon();
    }

    void SwitchWeapon()
    {
        float input = Input.GetAxis("Mouse ScrollWheel");
        currentWeaponIndex += weapons.Count + (int)(Mathf.CeilToInt(Mathf.Abs(input)) * Mathf.Sign(input));
        currentWeaponIndex = currentWeaponIndex % weapons.Count;
        if(weaponCtrl != weapons[currentWeaponIndex])
        {
            if(weaponCtrl != null)
            {
                weaponCtrl.gameObject.SetActive(false);
            }
            weaponCtrl = weapons[currentWeaponIndex];
            ikHandler.leftHandObj = weaponCtrl.leftHandObj;
            ikHandler.rightHandObj = weaponCtrl.rightHandObj;
            weaponCtrl.gameObject.SetActive(true);
        }
    }

    void Shoot()
    {
        if(Input.GetMouseButtonDown(0))
        {
            weaponCtrl.Fire();
        }
        if (Input.GetMouseButtonUp(0))
        {
            weaponCtrl.StopFiring();
        }
    }

    void Reload()
    {
        if(Input.GetKeyDown(KeyCode.R))
        {
            weaponCtrl.Reload();
        }
    }
}
