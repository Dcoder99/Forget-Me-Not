using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Collisions : MonoBehaviour
{
    static int v;
    public Text message;
    public GameObject ExplodePrefab;
    GameObject ExplodeObj;

    void Start()
    {
        message.text = "HEAD TO CHECKPOINT " + GameScript.targetIdx;
        message.color = Color.white;
    }

    void Delay()
    {
        Debug.Log("In Delay");
        ButtonsScript.loadSelectedScene("GameOver");
    }
    
    void Explode()
    {
        Destroy(ExplodeObj);
    }

    private void OnControllerColliderHit(ControllerColliderHit collision)
    {
        if (int.TryParse((collision.gameObject.name).Trim(), out v))
        {
            if (v == GameScript.targetIdx)
            {

                ExplodeObj = Instantiate(ExplodePrefab, collision.transform.position, new Quaternion(0, 0, 0, 0));
                Destroy(collision.gameObject);
                Invoke("Explode", 1.8f);

                Debug.Log("CORRECT ORDER");
                GameScript.targetIdx++;
                message.text = "HEAD TO CHECKPOINT " + GameScript.targetIdx;
                message.color = Color.green;

                if (GameScript.targetIdx > GameScript.maxCoinIdx)
                {
                    Debug.Log("LEVEL COMPLETED!");

                    message.text = "LEVEL COMPLETED!";
                    message.color = Color.green;

                    Invoke("Delay", 2);
                }

            }
            else if (v > GameScript.targetIdx)
            {
                message.text = "WRONG CHECKPOINT, GO TO " + GameScript.targetIdx;
                message.color = Color.red;
                Debug.Log("WRONG ORDER.");

                starScript.decrementScore(5);
            }
        }
    }
}