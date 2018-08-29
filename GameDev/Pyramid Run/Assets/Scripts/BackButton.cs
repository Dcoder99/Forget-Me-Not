using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class BackButton : MonoBehaviour {
	
	void FixedUpdate(){
        if (Application.platform == RuntimePlatform.Android)
        {
			if (Input.GetKey(KeyCode.Escape))
			{
				// UnityEngine.SceneManagement.LoadScene("GameSelector");
				Application.Quit();
			}
        }
    }
}
