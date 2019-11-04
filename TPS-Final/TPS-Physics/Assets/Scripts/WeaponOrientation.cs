using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WeaponOrientation : MonoBehaviour
{
    public Transform cameraTransform;
    public LayerMask aimLayers;
    public float maxAimDistance = 100;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        Vector3 lookAtPoint;
        RaycastHit hit;
        if(Physics.Raycast(cameraTransform.position,
                           cameraTransform.forward,
                           out hit,
                           maxAimDistance,
                           aimLayers))
        {
            lookAtPoint = hit.point;
        }
        else
        {
            lookAtPoint = cameraTransform.position + maxAimDistance * cameraTransform.forward;
        }
        transform.LookAt(lookAtPoint);
    }
}
