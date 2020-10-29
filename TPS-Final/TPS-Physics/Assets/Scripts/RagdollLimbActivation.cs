using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RagdollLimbActivation : MonoBehaviour
{
    public Transform ragdollLimb;
    
    public void SetRagdollTransform()
    {
        ragdollLimb.transform.position = transform.position;
        ragdollLimb.transform.rotation = transform.rotation;
    }
}
