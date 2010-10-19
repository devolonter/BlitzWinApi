Include "BlitzWinApi.bb"

Graphics3D 640,480,32,2
SetBuffer BackBuffer ()

cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()

bwalogo = LoadImage("bwalogo.jpg")

Repeat		
	If bGlobalMouseX()> bGetWindowX() And bGlobalMouseY()> bGetWindowY() And bGlobalMouseX()< bGetWindowRight() And bGlobalMouseY()< bGetWindowBottom() Then
		Cls
		TurnEntity cube,1,1,1
		UpdateWorld ()
		RenderWorld ()
		DrawImage bwalogo,540,380
		Flip()	
	End If
Until KeyHit (1)
