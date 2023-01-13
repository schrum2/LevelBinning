
import imageio
import os

path="/Users/amy/code/2023/01.jan/LevelBinning/gif"
filenames = os.listdir(path)

filenames.remove('.DS_Store')
filenames.sort()

for f in filenames:
    print(f)

images = []
for filename in filenames:
    filepath = f"{path}/{filename}"
    print(filepath)
    images.append(imageio.imread(filepath))
imageio.mimsave(f"{path}/loadrunner_full_gif.gif", images)