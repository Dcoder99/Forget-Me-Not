using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SceneBackToGenerate : MonoBehaviour {


	IEnumerator GoBackToGenerate()
	{
		yield return new WaitForSeconds(1);
		Application.LoadLevel("For Generating");
	}

	// Use this for initialization
	public void Start () {
		StartCoroutine(GoBackToGenerate());
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
