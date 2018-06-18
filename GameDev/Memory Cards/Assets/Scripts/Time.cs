using System.Collections.Generic;
using System.Collections;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class Time : MonoBehaviour {

    float Timeleft = 30f;
    public Text time_display;
	
	void Update () {
        Timeleft -= UnityEngine.Time.deltaTime;		
	}

    private void OnGUI()
    {
        if (Timeleft > 0f)
        {
            time_display.text = "Time: " + Timeleft.ToString("0");
        }

        else if (Timeleft < 0)
        {
            SceneManager.LoadScene("End Game");
        }
        
    }
}
