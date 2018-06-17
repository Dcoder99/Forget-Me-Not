using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class displayScore : MonoBehaviour {

	public Text disp, highsc;	
	// Use this for initialization
	void Start () {
		disp.text = "Score : " + controller.sc.ToString();
		highsc.text = "High Score : " + PlayerPrefs.GetInt("highscore", controller.sc);
	}
	
	public void BackToMain()
	{
		SceneManager.LoadScene("MainScene");
	}
	// Update is called once per frame
	void Update () {
		
	}
}
