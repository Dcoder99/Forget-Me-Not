using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class starScript : MonoBehaviour
{

    // Use this for initialization
    public Image stars;
    float TimeElapsed;
    static float score = 100;
    void Start()
    {
        TimeElapsed = progressBar.timeElapsed;
        Debug.Log("TIME: " + TimeElapsed);
        if (TimeElapsed > 0.33f)
        {
            if (TimeElapsed > 0.67f)
            {
                score -= 30;
            }
            else
            {
                score -= 20;
            }
        }
		stars.fillAmount = Mathf.Max(0.33f, score/100);
    }


    public static void decrementScore(int val)
    {
        score -= val;
    }
    // Update is called once per frame
}
