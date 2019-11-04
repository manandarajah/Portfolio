using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Fruit : MonoBehaviour
{
    public float maxSpinSpeed, speedBreak, speedDelimiter;
    private float spinSpeed = 0, scoreFloat = 0;
    [HideInInspector] public bool onConnector = false;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (spinSpeed > 0)
        {
            spinSpeed -= Time.deltaTime * speedBreak;
            transform.Rotate(new Vector3(0, spinSpeed, 0), Space.Self);
            scoreFloat += (spinSpeed / speedDelimiter);
            Gameplay.score += (int)scoreFloat;
        }

        else
        {
            transform.Rotate(Vector3.zero, Space.Self);
            scoreFloat = 0;
        }
    }

    public void Spin(Vector3 touchPos)
    {
        if (spinSpeed < maxSpinSpeed && !onConnector)
        {
            spinSpeed += Mathf.Abs(touchPos.x);
        }
    }

    public void ResetSpinSpeed()
    {
        spinSpeed = 0;
    }

    public float GetScore()
    {
        return scoreFloat;
    }

    public float GetSpeed()
    {
        return spinSpeed;
    }
}
