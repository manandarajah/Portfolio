using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class CollisionScript : MonoBehaviour
{
    public static Scrollbar health;

    // Start is called before the first frame update
    void Start()
    {
        health = GameObject.Find("Scrollbar").GetComponent<Scrollbar>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
