using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class maxMovements : MonoBehaviour {

    Animator animator;
    CharacterController c;
    GameObject va;
    static bool toMove = false, toTurnLeft = false, toTurnRight = false;
    public float speed = 3.0f;
    public float rotates = 3.0f;

    private void Start()
    {
        toMove = false; toTurnLeft = false; toTurnRight = false;
        animator = GetComponent<Animator>();
        va = GameObject.FindGameObjectWithTag("myMax");
        c = va.GetComponent<CharacterController>();
        //animator.SetBool("RUN", false);
        //animator.SetBool("LEFT", false);

    }


    private void Update()
    {
        if(toMove)
        {
            //c.Move(Vector3.forward * 2 * Time.deltaTime);
            Vector3 fo = va.transform.TransformDirection(Vector3.forward);
            c.Move(fo * 2 * Time.deltaTime);
        }
        if(toTurnLeft)
        {
            Debug.Log("in toturnLeft");
            va.transform.Rotate(0, -1 * rotates, 0);

        }

        if (toTurnRight)
        {
            Debug.Log("in toturnRight");
            va.transform.Rotate(0, 1 * rotates, 0);
        }

        if (SwipeManager.IsSwipingUp())
        {
            toMove = true;
            // c.Move(Vector3.forward * 2 * Time.deltaTime);
            animator.SetBool("LEFT", false);
            animator.SetBool("RUN", true);
        }

        if (SwipeManager.IsSwipingDown())
        {
            toMove = false;
            animator.SetBool("LEFT", false);
            animator.SetBool("RUN", false);
        }

        // if (SwipeManager.IsSwipingLeft())
        // {
        //     va.transform.Rotate(0, -1 * rotates, 0);
        //     animator.SetBool("LEFT", true);
        //     //animator.SetBool("RUN", );
        //    // Invoke("stopMovingLeft", 2);
           
        // }
    }

    public void TurnLeft()
    {
        Debug.Log("in left");
        toTurnLeft = true;
        // animator.SetBool("LEFT", toTurnLeft);
    }
    

    public void stopMovingLeft()
    {
        toTurnLeft = false;
        // animator.SetBool("LEFT", toTurnLeft);

    }

    public void TurnRight()
    {
        Debug.Log("in right");
        toTurnRight = true;
        // animator.SetBool("RIGHT", toTurnRight);
    }


    public void stopMovingRight()
    {
        toTurnRight = false;
        // animator.SetBool("RIGHT", toTurnRight);

    }
}