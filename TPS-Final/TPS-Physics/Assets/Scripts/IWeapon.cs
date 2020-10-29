using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public interface IWeapon
{
    void SetData(WeaponData data);
    void FireWeapon();
}
