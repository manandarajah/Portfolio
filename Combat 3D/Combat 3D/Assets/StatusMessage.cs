using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class StatusMessage : MonoBehaviour {

    private static string win = "You Win!!", lose = "You Lose :(", text = "";
    private static Text GUIText;

	// Use this for initialization
	void Start () {
        GUIText = this.gameObject.GetComponentInChildren<Text>();
	}

    public static void SetStatus(int playerScore, int matchPoint)
    {

        if (playerScore == matchPoint)
            text = win;
        else
            text = lose;
    }

    void UpdateText()
    {
        GUIText.text = text;
    }
	
	// Update is called once per frame
	void Update () {
        UpdateText();
	}
}
