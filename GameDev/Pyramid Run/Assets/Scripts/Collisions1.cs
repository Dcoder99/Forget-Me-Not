using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Collisions1 : MonoBehaviour
{
    private void OnCollisionEnter(Collision collision)
    {
        string[] strArr = null;
        char[] splitchar = { ' ' };
        strArr = (collision.gameObject.name).Split(splitchar);

        // Debug.Log("Collided with " + collision.gameObject.name);
        if (strArr[0] == "coin")
        {
            if (strArr[1] == GameScript.targetIdx.ToString())
            {
                Destroy(collision.gameObject);
                Debug.Log("CORRECT ORDER");
                GameScript.targetIdx++;

                if (GameScript.targetIdx > GameScript.maxCoinIdx)
                {
                    Debug.Log("LEVEL COMPLETED!");
                    // Game finished
                    // Change scene
                }
            }
            else
            {
                // message.text = "WRONG CHECKPOINT, GO TO " + strArr[1];
                Debug.Log("WRONG ORDER.");
                //Reduce stars
            }
        }
    }
}
