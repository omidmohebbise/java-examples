# Simple Example of Synchronization

In this example I tried to illustrate a basic view of synchronization.
</br>
<b>Synchronized method is used to lock an object for any shared resource. So countInputNumberFiveTimes in
SynchronizeResourceClass can be used by a thread simultaneously.</b>
</br>
To understand example one time run main method like this: </br>
<div style="background-color: lightgray; color:black; padding: 10px">
//without synchronization</br>
<b>t1.start();</b> </br>
<b>t2.start();</b></br>
//with synchronization</br>
//t3.start();</br>
//t4.start();</br>
</div>
another time run like this:</br>

<div style="background-color: lightgray; color:black; padding: 10px">
//without synchronization</br>
//t1.start(); </br>
//t2.start();</br>
//with synchronization</br>
<b>t3.start();</b></br>
<b>t4.start();</b></br>
</div>


