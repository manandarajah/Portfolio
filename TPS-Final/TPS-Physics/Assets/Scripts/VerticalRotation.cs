using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class VerticalRotation : MonoBehaviour
{
    [SerializeField]
    private float rotationSpeed = 100;
    [SerializeField]
    private float maxAngle = 60;
    public Transform lookAtPoint;

    float radius;
    float currentAngle = 0;

    // Start is called before the first frame update
    void Start()
    {
        radius = Vector3.Distance(lookAtPoint.position, transform.position);
    }

    // Update is called once per frame
    void Update()
    {
        Rotate();
    }

    void Rotate()
    {
        currentAngle += Input.GetAxis("Mouse Y") * rotationSpeed * Time.deltaTime;
        currentAngle = Mathf.Clamp(currentAngle, -maxAngle, maxAngle);

        float horizontalDist = radius * Mathf.Cos(currentAngle * Mathf.Deg2Rad);
        float verticalDist = radius * Mathf.Sin(currentAngle * Mathf.Deg2Rad);

        Vector3 horizontalUnitVector = transform.position - lookAtPoint.position;
        horizontalUnitVector.y = 0;
        horizontalUnitVector.Normalize();

        transform.position = lookAtPoint.position + 
                             horizontalDist * horizontalUnitVector + 
                             verticalDist * Vector3.up;

        transform.LookAt(lookAtPoint);
    }
}
