using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PositionCamera : MonoBehaviour {
	public static Vector3 pos= new Vector3(0.0f, 0.0f, 0.0f);

	// Use this for initialization
	void Start () {
		Debug.Log("In PositionCamera");
		
	}
	
	// Update is called once per frame
	void Update () {
		gameObject.transform.position = pos;
		
	}
}
