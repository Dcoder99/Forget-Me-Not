using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class progressBar : MonoBehaviour {

	// Use this for initialization
	public Image progress;
	
	// Update is called once per frame
	void Update () {
		progress.fillAmount -= (Time.deltaTime*0.01f);
	}
}
