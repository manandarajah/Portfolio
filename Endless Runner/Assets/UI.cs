using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UI : MonoBehaviour {

    public static int Score = 0;
    private float duration = 0;
    private float counter = 0, delayTime = 1f;
    private Text durationText;

	// Use this for initialization
	void Start () {
        durationText = GameObject.Find("DurationText").GetComponent<Text>();
	}

    void DurationUpdate()
    {
        if (GamePlay.Run)
        {
            if (counter > delayTime)
            {
                durationText.text = duration++ + " s.mm";
                counter = 0;
            }
            counter += Time.deltaTime;
        }
    }
	
	// Update is called once per frame
	void Update () {
        DurationUpdate();
	}
}
