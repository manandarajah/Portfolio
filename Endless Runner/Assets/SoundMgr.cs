using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SoundMgr : MonoBehaviour {

    public static AudioSource[] sources;

	// Use this for initialization
	void Start () {
        sources = this.GetComponents<AudioSource>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
