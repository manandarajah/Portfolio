using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player2Tank : TankControl {

	// Use this for initialization
	void Start () {
        rb = this.GetComponent<Rigidbody>();
        tank = this.gameObject;
	}

    public void Player2TankUpdate()
    {
        if (Input.GetKey(KeyCode.W)
            || Input.GetKey(KeyCode.S)
            || Input.GetKey(KeyCode.A)
            || Input.GetKey(KeyCode.D))
            move = true;

        if (Input.GetButton("Fire2"))
            fire = true;
        TankCtrlUpdate();
    }

    // Update is called once per frame
    void Update () {
        Player2TankUpdate();
    }
}
