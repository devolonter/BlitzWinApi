; Run together Sendmessage_server_example.bb and Sendmessage_cleint_example.bb
; On the server right click on the left and look what happened to the client

Include "blitzwinapi.bb"

AppTitle  "server"
Graphics3D 640,480,32,2
SetBuffer BackBuffer ()

cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()

Repeat
	client = bFindWindow("client")
	Cls
		If KeyDown (203) Then
			bSendMessage (client,1013)
			TranslateEntity cube, -0.1,0,0
		End If
		If KeyDown (205) Then
			bSendMessage (client,1014)
			TranslateEntity cube, 0.1,0,0
		End If
		TurnEntity cube,1,1,1
		UpdateWorld ()
		RenderWorld ()
	Flip()	
Until KeyHit (1)