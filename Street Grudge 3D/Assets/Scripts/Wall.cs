using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Wall : MonoBehaviour
{

    // Start is called before the first frame update
    void Start()
    {
        GetComponent<Rigidbody>().constraints = RigidbodyConstraints.FreezeAll;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnCollisionEnter(Collision collision)
    {
        GameObject obj = collision.gameObject;

        if (obj.name.Equals("Player") || obj.tag.Equals("Enemy"))
        {
            obj.transform.Translate(Vector3.back);
        }
    }
}
