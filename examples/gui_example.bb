Include "BlitzWinApi.bb"

AppTitle "SimpleGui"

Graphics3D 640,480,32,2
SetBuffer BackBuffer ()

cube = CreateCube ()
PositionEntity cube, 0,0,5
camera = CreateCamera ()
light = CreateLight ()

bwalogo = LoadImage("bwalogo.jpg")

menu = bCreatePopupMenu ()
	options = bAddPopupItem (menu,"options")
		wire = bAddMenuItem (options,201,"wireframe")
		col = bAddMenuItem (options,202,"change color...")
		tex = bAddMenuItem (options,203,"change texture...")
	bAddMenuItem (menu,0,"",MF_SEPARATOR)
	about = bAddMenuItem (menu,101,"about")
	ex = bAddMenuItem (menu,102,"exit")
Repeat
	Cls
		If MouseHit (2) Then
			result = bRequestPopupMenu(menu)
		Select result
			Case Wire
				If Not bGetMenuItemState(menu,wire) Then
					bCheckMenuItem(menu,wire)
				Else
					bUncheckMenuItem(menu,wire)
				End If
			Case Col
				bRequestColor()
				EntityColor cube, bRequestRed(), bRequestGreen(), bRequestBlue()
			Case Tex
				file$ = bRequestFile ("Images |*.bmp;*.jpg")
					If file$ <> "" Then
						If texture <> 0 FreeTexture(Texture)
						Texture = LoadTexture(file)
						If texture <> 0 EntityTexture (cube,Texture)
					End If
			Case ex
				msg = bMessageBox("Вы уверены что хотите выйти?","SimpleGui",MB_OKCANCEL)
				If msg = IDOK Then
					bDestroyMenu (menu)
					End
				End If
			Case about
				bMessageBox("Пример работы библиотеки BlitzWinApi"+Chr(13)+"Автор: devolonter (devolonter@mail.ru)","SimpleGui")
		End Select
		End If
		WireFrame bGetMenuItemState(menu,wire)
		TurnEntity cube,1,1,1
		UpdateWorld ()
		RenderWorld ()
		DrawImage bwalogo,540,380
	Flip()	
Until KeyHit (1)
bDestroyMenu (menu)