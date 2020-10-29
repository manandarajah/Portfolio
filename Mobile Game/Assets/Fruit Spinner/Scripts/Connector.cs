using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Connector : MonoBehaviour
{
    public float maxSpinSpeed, speedBreak, speedDelimiter;
    private float spinSpeed = 0, scoreFloat = 0;
    [HideInInspector] public bool isConnected = false;
    Vector3 touchPos;

    // Start is called before the first frame update
    void Start()
    {
        touchPos = this.transform.position;
    }

    // Update is called once per frame
    void Update()
    {
        if (!isConnected)
        {
            this.transform.position = touchPos;
        }

        if (spinSpeed > 0)
        {
            spinSpeed -= Time.deltaTime * speedBreak;
            transform.Rotate(new Vector3(0, spinSpeed, 0), Space.Self);
            scoreFloat += (spinSpeed / speedDelimiter) * 2;
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
        if (spinSpeed < maxSpinSpeed)
        {
            spinSpeed += Mathf.Abs(touchPos.x);
        }
    }

    public float GetScore()
    {
        return scoreFloat;
    }

    public void Stretch()
    {

    }

    public void Move(Vector3 touchPos)
    {
        this.touchPos = touchPos;
    }

    void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.name.Equals("AttachPoint1") 
            || collision.gameObject.name.Equals("AttachPoint2"))
        {
            Debug.Log(this.transform.position);
            collision.gameObject.transform.parent.gameObject.transform.SetParent(transform);
            collision.gameObject.transform.parent.gameObject.GetComponent<Fruit>().onConnector = true;
            Destroy(gameObject.GetComponent<Rigidbody>());
            isConnected = true;
        }
    }
}
