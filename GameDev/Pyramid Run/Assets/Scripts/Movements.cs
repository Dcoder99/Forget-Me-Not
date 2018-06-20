using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movements : MonoBehaviour {

    public Rigidbody rb;
    private Animator anim ;
    public static bool runState = false;     //Initially MAX is idle.

    void Start()
    {
        anim = gameObject.GetComponent<Animator>();
                                  

    }

    void Update()
    {
        anim.SetBool("RUN", runState);
        if (SwipeManager.IsSwipingUp())                         //On Swipe up, MAX moves forward.
        {
            runState = true;
        }
        if (SwipeManager.IsSwipingDown())                       //On Swipe down, MAX is idle.
        {
            runState = false;
        }
        if (runState)
        {
            rb.velocity = new Vector3(rb.velocity.x, rb.velocity.y, 10);
        }
    }

}


