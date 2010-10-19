Include "BlitzWinApi.bb"

bDragAcceptFiles  ()

Graphics3D 640,480,32, 2
SetBuffer BackBuffer ()

cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()

bwalogo = LoadImage("bwalogo.jpg")

Repeat
	Cls
	Texture$ = bWaitDrop ()
	If Texture <> "" Then
		tex = LoadTexture (Texture)
		If tex <> 0
			EntityTexture cube, tex
		End If
		Texture = ""
	End If
	TurnEntity cube,1,1,1	
	UpdateWorld ()
	RenderWorld ()
	DrawImage bwalogo,540,380
	Text 0,0,"Drag&drop texture to cube"
	Flip (0)
Until KeyHit (1)