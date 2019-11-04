using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu(fileName = "Data", menuName = "ScriptableObjects/WeaponData", order = 1)]
public class WeaponData : ScriptableObject
{
    public float damage = 10;
    public int magazineSize = 30;
    public float fireRate = 5;
    public float range = 100;
}
