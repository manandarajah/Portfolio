using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PendulumMotion : MonoBehaviour
{
    public GameObject player;
    private float timer = 0, phase = 0, speed = 5;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (player.GetComponent<GrapplingHook>().hooked)
        {
            timer += Time.fixedDeltaTime;

            if (timer > 1)
            {
                ++phase;
                phase %= 4;
                timer = 0;
            }

            switch (phase)
            {
                case 0:
                    //transform.Rotate(0, 0, speed * (1 - timer));
                    player.transform.RotateAround(transform.position, Vector3.forward, speed * (1 - timer));
                    break;
                case 1:
                    //transform.Rotate(0, 0, -speed * timer);
                    player.transform.RotateAround(transform.position, Vector3.forward, -speed * timer);
                    break;
                case 2:
                    //transform.Rotate(0, 0, -speed * (1 - timer));
                    player.transform.RotateAround(transform.position, Vector3.forward, -speed * (1 - timer));
                    break;
                case 3:
                    //transform.Rotate(0, 0, speed * timer);
                    player.transform.RotateAround(transform.position, Vector3.forward, speed * timer);
                    break;
            }
        }
    }
}
