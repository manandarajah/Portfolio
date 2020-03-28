using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class BackManager : MonoBehaviour
{

    // Start is called before the first frame update
    void Start()
    {
        GameObject.Find("BackToTitle").GetComponent<Button>().onClick.AddListener(BackToTitle);
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void BackToTitle()
    {
        SceneManager.LoadScene(0);
    }
}
