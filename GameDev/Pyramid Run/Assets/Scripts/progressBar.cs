using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class progressBar : MonoBehaviour
{

    // Use this for initialization
    public Image progress;
    static float totalTime = 100.0f;
    public static float timeElapsed = 0.0f;
   // public static float getTime()
    //{
      //  Debug.Log("Time elpsed: " + timeElapsed);
        //return timeElapsed;
    //}

    public static void setTotalTime(float time)
    {
        totalTime = time;
    }

    void Start()
    {
        progress.fillAmount = 1;
        timeElapsed = 0.0f;
    }

    // Update is called once per frame
    void Update()
    {
        timeElapsed += (Time.deltaTime / totalTime);
        progress.fillAmount = 1 - timeElapsed;
    }
}
