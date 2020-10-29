using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LineDetector : MonoBehaviour
{
    bool lineDetect = false;
    public Material indicatorMaterial;
    Material defaultMaterial;

    // Start is called before the first frame update
    void Start()
    {
        defaultMaterial = this.GetComponent<MeshRenderer>().material;
    }

    // Update is called once per frame
    void Update()
    {
        if (lineDetect)
        {
            this.GetComponent<MeshRenderer>().material = indicatorMaterial;
        }

        else
        {
            this.GetComponent<MeshRenderer>().material = defaultMaterial;
        }
    }

    void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.name.Equals("IndicatorLine"))
        {
            lineDetect = true;
        }
    }

    private void OnCollisionExit(Collision collision)
    {
        if (collision.gameObject.name.Equals("IndicatorLine"))
        {
            lineDetect = false;
        }
    }
}
