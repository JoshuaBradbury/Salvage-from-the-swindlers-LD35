from PIL import Image
from random import randint as r

names = ["rain"]

for n in range(len(names)):
    img = Image.open(names[n] + ".png")
    img = img.convert("RGBA")

    tilesX = 5
    tilesY = 1
    tileWidth = int(img.width / tilesX)
    tileHeight = int(img.height / tilesY)

    count = 0
    for y in range(tilesY):
        for x in range(tilesX):
            temp = Image.new("RGBA", [tileWidth, tileHeight], (255, 255, 255, 255))
            for i in range(tileWidth):
                for j in range(tileHeight):
                    temp.putpixel((i, j), img.getpixel(((x * tileWidth) + i, (y * tileHeight) + j)))
            temp.save(names[n] + "/" + names[n] + str(count) + ".png")
            temp.close()
            count += 1
    img.close()

