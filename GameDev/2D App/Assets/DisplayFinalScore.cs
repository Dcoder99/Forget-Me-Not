using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;





public class DisplayFinalScore : MonoBehaviour {
	public Text FinalScore;
	int FinalS;

	public void GoBackToGenerate()
	{
		Application.LoadLevel("For Generating");

	}
	// Use this for initialization
	void Start () {
		FinalScore.text= "Final Score: " + ToGenerate.score.ToString();
		ToGenerate.score=0;
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
