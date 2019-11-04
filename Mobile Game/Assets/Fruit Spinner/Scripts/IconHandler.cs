using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IconHandler : MonoBehaviour
{
    public GameObject connector, connectorSpawner;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void CreateConnector(Vector3 touchPos)
    {
        GameObject tmp = Instantiate(connector, connectorSpawner.transform.position, Quaternion.identity);
    }
}
