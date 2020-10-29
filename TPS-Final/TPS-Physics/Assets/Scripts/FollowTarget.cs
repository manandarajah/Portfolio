using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FollowTarget : MonoBehaviour
{
    public Transform followTarget;
    public Transform lookAtTarget;
    public float followDistance = 5;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        SetPosition();
        LookAt();
    }

    void SetPosition()
    {
        transform.position = followTarget.position - followDistance * followTarget.forward;
    }

    void LookAt()
    {
        transform.LookAt(lookAtTarget);
    }
}
