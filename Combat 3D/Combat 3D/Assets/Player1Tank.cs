using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player1Tank : TankControl {

    // Use this for initialization
    void Start () {
        rb = this.GetComponent<Rigidbody>();
        tank = this.gameObject;
	}

    public void Player1TankUpdate()
    {
        if (Input.GetKey(KeyCode.UpArrow)
            || Input.GetKey(KeyCode.DownArrow)
            || Input.GetKey(KeyCode.LeftArrow)
            || Input.GetKey(KeyCode.RightArrow))
            move = true;

        if (Input.GetButton("Fire1"))
            fire = true;
        TankCtrlUpdate();
    }
	
	// Update is called once per frame
	void Update () {
        Player1TankUpdate();
	}
}
