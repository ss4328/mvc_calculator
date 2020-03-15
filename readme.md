# MVC Client/Server Calculator

This project is made to understand common design patterns: MVC, client-server, Observer, State, Visitor, and Composite.

## Execution

Project needs java to run and intellij IDEA to view source. Artifacts are in src/out/. Double click on the jar to run, followed by executing server similarly or by running:

```bash
java server.class
```

##Notes
Main Driver causes the client to start.Client leverages its MVC pattern to start and operate the calculator; user communicates via View class's Swing/awt GUI.


## Design 

**Please read in sequence for better comprehension.**

### Client/Server 
Calculator has it's own client in Network package. The client is a very basic implementation of a simple web client that runs on port 8080, and host configurable in source code. I'll probably make a config file in the future to east the usage and astract this possible change. 

Server has its own driver and handler classes which accept input from the client operating on the same host and port. Both the server and client use VisitorLib package, a very small sorta-library of my own. 

### Visitor Pattern
Alongwith Composite Pattern, Visitor pattern is used via it's files in VisitorLib Package. Equations have their own accept(Visitor) methods and Visitors must have a visit(Equation) method. NodeVisitor traverses the node. 

### Composite Pattern
Composite pattern is used via the declaration and usage of Node class. Each Equation implements the Node interface, and so does Number and Operation Class. The code is again in VisitorLib package. 
Operation is therefore a Concrete Class of type Node.
Number is also a Concrete Class of type Node. 

### State Pattern
Each variable needed in calculator(s1,op,s2) maintains its own state. 

### Observer Pattern
The Controller is the observer to View (subject). Implicit invokation is employed via builtin ActionEvents and ActionListeners whenever an event triggers.

## Notes
- Main Driver causes the client to start.
- Client leverages its MVC pattern to start and operate the calculator; user communicates via View (basically aids visualization), Model controls the logic, and Controller binds Model/View, controls data-flow into model, and updates view when data changes. Controller also keeps Model/View separate
- Its extremely noteworthy that Swing/AWT method isn't technically considered pure MVC implementation.  It borrows heavily from the concept, but "collapses" the "view and controller" together, sorta.

- View communicates to model via controller; view has several components that are created using composite pattern.
- Different events in Controller trigger events that call model for data
- Server/client atchitecture is used: Calculator is client, calculator_server is server
	-Client sends equations to server by serializing them, server deserializes and processes them
- Visitor pattern is used by using abstracting library from extra-credit assignment

-Source code is in src/


## Explain me like I'm Five (maybe 12?)?
### MVC
Credits to stackOverflow/bnrdo
My idea of MVC (which I'm currently working with, so far so good) is :

1. The view is the dumbest of the three. It doesn't know anything about the controller and the model. Its concern is only the swing components' prostethics and layout.
2. The model is also dumb, but not as dumb as the view. It performs the following functionalities.
- when one of its setter is called by the controller, it will fire notification to its listeners/observers (like I said, I would deligate this role to the controller). I prefer SwingPropertyChangeSupport for achieving this since its already optimized for this purpose.
- database interaction functionality.
3. A very smart controller. Knows the view and the model very well. The controller has two functionalities:
- It defines the action that the view will execute when the user interacts to it.
- It listens to the model. Like what I've said, when the setter of the model is called, the model will fire notification to the controller. It's the controller's job to interpret this notification. It might need to reflect the change to the view.

### Observer
It lets you set up objects that ought to respond in some way to some change or "message" from another object. Those listening objects are called subscribers (observers) and the changing object is the publisher.

Let's say you have an object that's doing some work and when its finished you'd like a couple of other things to happen. Maybe it's a component in an app loading and rendering. When it's ready you'd like a couple of other components (objects) that depend on it to do their stuff too.

So you could have a sort of manager process that attempts to check repeatedly for the first component to be ready and when it is, to call methods in the other objects. But this isn't very efficient.

Instead, you have a method on the first object whereby other interested objects can "register" that they'd like to be told when something had happened, something like "onLoadComplete()" for example. The first object is now a "publisher" and the waiting / listening objects are either called "subscribers" or "observers"

When the first object has finished its business (the component has loaded) it either calls a method (or raises an event, depending on your programming language) that indicates it's done loading and the subscribers should be told. Methods in each of the subscribed object are then called giving them opportunity to run code in response to the change that occurred in the first object.

## Visitor
Consider yourself list in a maze with rooms and doors, you open(accept) each door that comes your way and explore it for next door that leads out of it. You're the visitor, you visit rooms. 

## Composite
Again, continue in the maze. Imagine you reach a room with no doors; that's a leaf. If the room has door that leads to more rooms (i.e. think room contains more rooms) it's a composite.