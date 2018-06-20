using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CollectibleToken : MonoBehaviour {

    private Text scoreText;

    public void OnCollisionEnter(Collision other)
    {
        switch (other.gameObject.tag)
        {
            case "Red":
                UI.Score += 5;
                Destroy(other.gameObject);
                SoundMgr.sources[0].Play();
                break;
            case "Blue":
                UI.Score += 3;
                Destroy(other.gameObject);
                SoundMgr.sources[0].Play();
                break;
        }

        if (scoreText != null)
            scoreText.text = "Score: " + UI.Score;
    }

    // Use this for initialization
    void Start () {
        scoreText = GameObject.Find("ScoreText").GetComponent<Text>();
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
