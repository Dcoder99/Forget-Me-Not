using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class displayScore : MonoBehaviour {
	// Use this for initialization	
	public Text disp, highsc;	

	void Start () {
		disp.text = "Score : " + controller.sc.ToString();
		highsc.text = "High Score : " + PlayerPrefs.GetInt("highscore", controller.sc);
	}
	
	public void BackToMain()
	{
		SceneManager.LoadScene("MainScene");
	}
}
