# Java Concurrency / Multithreading Tutorial


Multithreading can be a great way to increase the performance of some types of programs. 
However, mulithreading is even more challenging than multitasking

The threads are executing within the same program and are hence reading and writing the same memory simultanously

If a thread reads a memory location while another thread writes to it, what value will the first thread end up reading? 
The old value? The value written by the second thread? Or a value that is a mix between the two? Or, 
if two threads are writing to the same memory location simultanously, what value will be left when they are done? 
The value written by the first thread? The value written by the second thread? Or a mix of the two values written?

Multithreading and Concurrency in Java

ava was one of the first languages to make multithreading easily available to developers.


# Multithreading Benefits

The reason multithreading is still used in spite of its challenges is that multithreading can have several benefits. Some of these benefits are:


Better resource utilization.

Simpler program design in some situations.

More responsive programs.