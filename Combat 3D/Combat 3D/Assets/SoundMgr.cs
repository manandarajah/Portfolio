using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SoundMgr : MonoBehaviour {

    public static AudioSource MoveTank, TankDamage, TankFire;

	// Use this for initialization
	void Start () {
        MoveTank = GameObject.Find("MoveTank").GetComponentInChildren<AudioSource>();
        TankDamage = GameObject.Find("TankDamage").GetComponentInChildren<AudioSource>();
        TankFire = GameObject.Find("TankFire").GetComponentInChildren<AudioSource>();
    }
	
	// Update is called once per frame
	void Update () {
		
	}
}
