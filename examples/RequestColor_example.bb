Include "BlitzWinApi.bb"

Graphics3D 640,480,32
SetBuffer BackBuffer ()

cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()

bwalogo = LoadImage("bwalogo.jpg")

Repeat
	Cls
	If KeyHit (57) Then
		bRequestColor()
		EntityColor (cube, bRequestRed(), bRequestGreen(), bRequestBlue())
	End If
	TurnEntity cube,1,1,1	
	UpdateWorld ()
	RenderWorld ()
	DrawImage bwalogo,540,380
	Text 0,0,"press space to select cube color"
	Flip ()
Until KeyHit (1)