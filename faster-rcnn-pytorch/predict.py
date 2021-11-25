from frcnn import FRCNN
from PIL import Image

frcnn = FRCNN()
while True:
    try:
        with open("C:\\Users\\张浩天\\Desktop\\PestsIdentification\\web\\WEB-INF\\saved\\file.txt","r") as f:
            name=f.read()
        path="C:\\Users\\张浩天\\Desktop\\PestsIdentification\\web\\WEB-INF\\saved\\"+name
        path2="C:\\Users\\张浩天\\Desktop\\PestsIdentification\\out\\artifacts\\PestsIdentification_war_exploded\\WEB-INF\\saved\\"+name
        image = Image.open(path2)
    except:
        print('')
    else:
        r_image = frcnn.detect_image(image)
        r_image.save(path)
        r_image.save(path2)
        #r_image.show()
    


