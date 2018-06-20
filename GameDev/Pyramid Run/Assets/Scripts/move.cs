using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class move : MonoBehaviour {

    public Rigidbody rb;


    void Start()
    {
       
    }

    void FixedUpdate()
    {
        Debug.Log("in update");

        rb.velocity = new Vector3(rb.velocity.x, rb.velocity.y, 15);
        //rb.AddForce(0, 0.5f, 1000f * Time.deltaTime);
    }

}
