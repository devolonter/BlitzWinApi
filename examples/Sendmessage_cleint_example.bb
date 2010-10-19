; Run together Sendmessage_server_example.bb and Sendmessage_cleint_example.bb
; On the server right click on the left and look what happened to the client

Include "blitzwinapi.bb"

AppTitle  "client"
Graphics3D 640,480,32,2
SetBuffer BackBuffer ()

cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()

Repeat
	Cls
		If bGetEvent() = 1013  Then
			TranslateEntity cube, -0.1,0,0
		End If
		If bGetEvent() = 1014  Then
			TranslateEntity cube, 0.1,0,0
		End If
		TurnEntity cube,1,1,1
		UpdateWorld ()
		RenderWorld ()
	Flip()	
Until KeyHit (1)
