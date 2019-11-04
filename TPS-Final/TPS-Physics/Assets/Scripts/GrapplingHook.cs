using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GrapplingHook : MonoBehaviour
{
    public GameObject hook, hookHolder, hookObj;
    public float travelSpeed, playerTravelSpeed, maxDistance;
    private float currentDistance;
    public static bool fired = false;
    public bool hooked;

    void Update()
    {
        if (Input.GetMouseButtonUp(1) && !fired)
        {
            fired = true;
        }

        if (fired && !hooked)
        {
            hook.transform.Translate(Vector3.forward * Time.deltaTime * travelSpeed);
            currentDistance = Vector3.Distance(transform.position, hook.transform.position);

            if (currentDistance >= maxDistance)
            {
                ReturnHook();
            }
        }

        if (hooked)
        {
            //Change this code into a pendulum swing
            /*hook.transform.parent = hookObj.transform;
            transform.position = Vector3.MoveTowards(transform.position, hook.transform.position, Time.deltaTime * playerTravelSpeed);*/
            hook.transform.parent = hookObj.transform;
            float distanceToHook = Vector3.Distance(transform.position, hook.transform.position);

            if (distanceToHook < 1)
            {
                ReturnHook();
            }
        }

        else
        {
            hook.transform.parent = hookHolder.transform;
        }

        if (Input.GetKeyUp(KeyCode.F) && hooked)
        {
            ReturnHook();
        }
    }

    void ReturnHook()
    {
        hook.transform.rotation = hookHolder.transform.rotation;
        hook.transform.position = hookHolder.transform.position;
        fired = false;
        hooked = false;
    }

    private void OnCollisionEnter(Collision collision)
    {
        if (hooked && (collision.gameObject.tag.Equals("Wall") || collision.gameObject.tag.Equals("Enemy") || collision.gameObject.name.Equals("Ground")))
        {
            Debug.Log("Collide");
            GetComponent<PlayerMovementController>().enabled = true;
            transform.rotation = GetComponent<PlayerMovementController>().ogRotation;
            ReturnHook();
        }
    }
}
