using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TokenAnimator : MonoBehaviour {

    private GameObject token;

	// Use this for initialization
	void Start () {
        token = this.gameObject;
	}
	
	// Update is called once per frame
	void Update () {
        token.transform.RotateAround(Vector3.up, 1f);
	}
}
